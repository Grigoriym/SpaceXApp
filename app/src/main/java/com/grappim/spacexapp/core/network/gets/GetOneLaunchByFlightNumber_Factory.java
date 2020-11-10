// Generated by Dagger (https://dagger.dev).
package com.grappim.spacexapp.core.network.gets;

import com.grappim.spacexapp.data.remote.SpaceXRepository;
import dagger.internal.Factory;
import javax.inject.Provider;

@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class GetOneLaunchByFlightNumber_Factory implements Factory<GetOneLaunchByFlightNumber> {
  private final Provider<SpaceXRepository> spaceXRepositoryProvider;

  public GetOneLaunchByFlightNumber_Factory(Provider<SpaceXRepository> spaceXRepositoryProvider) {
    this.spaceXRepositoryProvider = spaceXRepositoryProvider;
  }

  @Override
  public GetOneLaunchByFlightNumber get() {
    return newInstance(spaceXRepositoryProvider.get());
  }

  public static GetOneLaunchByFlightNumber_Factory create(
      Provider<SpaceXRepository> spaceXRepositoryProvider) {
    return new GetOneLaunchByFlightNumber_Factory(spaceXRepositoryProvider);
  }

  public static GetOneLaunchByFlightNumber newInstance(SpaceXRepository spaceXRepository) {
    return new GetOneLaunchByFlightNumber(spaceXRepository);
  }
}
