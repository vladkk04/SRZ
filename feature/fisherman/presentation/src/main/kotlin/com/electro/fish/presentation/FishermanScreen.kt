@file:OptIn(ExperimentalMaterial3ExpressiveApi::class)

package com.electro.fish.presentation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun FishermanScreen() {
    val viewModel: FishermanViewModel = viewModel()

    GuestScreenContent()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun GuestScreenContent() {

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
    ) {

    }


    /*Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        HorizontalFloatingToolbar(
            expanded = false,
            modifier = Modifier.align(Alignment.BottomCenter),
        ) {
            AppIconButton(
                imageVector = Icons.Default.Home,
                onClick = {}
            )

            AppIconButton(
                imageVector = Icons.Default.Add,
                onClick = {}
            )

            AppIconButton(
                imageVector = Icons.Default.Person,
                onClick = {}
            )
        }


    }*/
}

/*
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SimpleSearchBar(
    textFieldState: TextFieldState,
    onSearch: (String) -> Unit,
    searchResults: List<String>,
    modifier: Modifier = Modifier
) {
    var expanded by rememberSaveable { mutableStateOf(false) }

    Box(
        modifier
            .fillMaxSize()
            .semantics { isTraversalGroup = true }
    ) {
        SearchBar(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .semantics { traversalIndex = 0f },
            inputField = {
                SearchBarDefaults.InputField(
                    query = textFieldState.text.toString(),
                    onQueryChange = { textFieldState.edit { replace(0, length, it) } },
                    onSearch = {
                        onSearch(textFieldState.text.toString())
                        expanded = false
                    },
                    expanded = expanded,
                    onExpandedChange = { expanded = it },
                    placeholder = { TextInputField("Search") }
                )
            },
            expanded = expanded,
            onExpandedChange = { expanded = it },
        ) {
            Column(Modifier.verticalScroll(rememberScrollState())) {
                searchResults.forEach { result ->
                    ListItem(
                        headlineContent = { TextInputField(result) },
                        modifier = Modifier
                            .clickable {
                                textFieldState.edit { replace(0, length, result) }
                                expanded = false
                            }
                            .fillMaxWidth()
                    )
                }
            }
        }
    }
}*/
