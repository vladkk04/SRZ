package com.electro.fish.splash;

import com.electro.fish.splash.navigation.SignInNavigator;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

@ScopeMetadata
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava",
    "cast",
    "deprecation",
    "nullness:initialization.field.uninitialized"
})
public final class SignInViewModel_Factory implements Factory<SignInViewModel> {
  private final Provider<SignInNavigator> navigatorProvider;

  private SignInViewModel_Factory(Provider<SignInNavigator> navigatorProvider) {
    this.navigatorProvider = navigatorProvider;
  }

  @Override
  public SignInViewModel get() {
    return newInstance(navigatorProvider.get());
  }

  public static SignInViewModel_Factory create(Provider<SignInNavigator> navigatorProvider) {
    return new SignInViewModel_Factory(navigatorProvider);
  }

  public static SignInViewModel newInstance(SignInNavigator navigator) {
    return new SignInViewModel(navigator);
  }
}
