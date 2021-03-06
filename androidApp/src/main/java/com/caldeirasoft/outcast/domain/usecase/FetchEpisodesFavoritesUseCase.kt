package com.caldeirasoft.outcast.domain.usecase

import com.caldeirasoft.outcast.domain.models.EpisodeSummary
import com.caldeirasoft.outcast.domain.repository.EpisodeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class FetchEpisodesFavoritesUseCase @Inject constructor(
    val episodeRepository: EpisodeRepository)
    : FlowUseCase<FetchEpisodesFavoritesUseCase.Params, List<EpisodeSummary>> {
    override fun execute(param: Params) =
        when {
            param.podcastId != null -> episodeRepository.fetchEpisodesFavoritesByPodcastId(param.podcastId)
            else -> episodeRepository.fetchEpisodesFavorites()
        }

    data class Params(val podcastId: Long?)
}