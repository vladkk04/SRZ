package com.electro.fish.ui.component

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.LayoutScopeMarker
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerDefaults
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import kotlinx.collections.immutable.ImmutableList

@LayoutScopeMarker
@Immutable
interface PagerScope {

    val pageCount: Int

    fun getCurrentPage(): Int

    suspend fun next()

    suspend fun previous()

    fun requestNext()

    fun requestPrevious()
}

@Immutable
interface PagerScreenFeature {
    val content: @Composable PagerScope.() -> Unit
}

@Stable
interface PagerContentContainer <T: PagerScreenFeature> {
    @Composable
    fun Wrap (screenFeature: T, content: @Composable () -> Unit)
}

private class AppPagerScopeImpl(
    private val pagerState: PagerState,
) : PagerScope {

    override val pageCount: Int = pagerState.pageCount

    override fun getCurrentPage(): Int = pagerState.currentPage

    private fun nextPage() = (getCurrentPage() + 1)
        .coerceAtMost(pagerState.pageCount - 1)

    private fun previousPage() = (getCurrentPage() - 1)
        .coerceAtLeast(0)

    override suspend fun next() {
        pagerState.animateScrollToPage(nextPage())
    }

    override suspend fun previous() {
        pagerState.scrollToPage(previousPage())
    }

    override fun requestNext() {
        pagerState.requestScrollToPage(nextPage())
    }

    override fun requestPrevious() {
        pagerState.requestScrollToPage(previousPage())
    }
}

@Composable
fun <T: PagerScreenFeature> AppHorizontalPager(
    screens: ImmutableList<T> ,
    modifier: Modifier = Modifier,
    initialPage: Int = 0,
    fillMaxSize: Boolean = true,
    contentContainer: PagerContentContainer<T>? = null,
    titleContainer: (@Composable ColumnScope.(PagerScope) -> Unit)? = null,
    bottomContainer: (@Composable ColumnScope.(PagerScope) -> Unit)? = null,
    userScrollEnabled: Boolean = true,
    beyondViewportPageCount: Int = PagerDefaults.BeyondViewportPageCount,
) {
    val pagerState = rememberPagerState(initialPage) { screens.size }
    val pagerScoreImpl = remember { AppPagerScopeImpl(pagerState) }

    BackHandler(
        enabled = pagerState.currentPage != 0,
    ) { pagerScoreImpl.requestPrevious() }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        titleContainer?.invoke(this, pagerScoreImpl)

        val pagerModifier = if (fillMaxSize) {
            Modifier.weight(1f)
        } else {
            Modifier.wrapContentHeight()
        }

        HorizontalPager(
            state = pagerState,
            userScrollEnabled = userScrollEnabled,
            beyondViewportPageCount = beyondViewportPageCount,
            modifier = pagerModifier
        ) {
            contentContainer?.Wrap(screens[it]) {
                screens[it].content.invoke(pagerScoreImpl)
            } ?: screens[it].content.invoke(pagerScoreImpl)
        }

        bottomContainer?.invoke(this, pagerScoreImpl)
    }
}