// Generated by Dagger (https://dagger.dev).
package com.grappim.spacexapp.core.network.gets;

import com.grappim.spacexapp.data.remote.SpaceXRepository;
import dagger.internal.Factory;
import javax.inject.Provider;

@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class GetUpcomingCores_Factory implements Factory<GetUpcomingCores> {
  private final Provider<SpaceXRepository> spaceXRepositoryProvider;

  public GetUpcomingCores_Factory(Provider<SpaceXRepository> spaceXRepositoryProvider) {
    this.spaceXRepositoryProvider = spaceXRepositoryProvider;
  }

  @Override
  public GetUpcomingCores get() {
    return newInstance(spaceXRepositoryProvider.get());
  }

  public static GetUpcomingCores_Factory create(
      Provider<SpaceXRepository> spaceXRepositoryProvider) {
    return new GetUpcomingCores_Factory(spaceXRepositoryProvider);
  }

  public static GetUpcomingCores newInstance(SpaceXRepository spaceXRepository) {
    return new GetUpcomingCores(spaceXRepository);
  }
}
