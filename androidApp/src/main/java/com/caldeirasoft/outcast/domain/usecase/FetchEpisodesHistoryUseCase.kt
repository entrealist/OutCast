package com.caldeirasoft.outcast.domain.usecase

import com.caldeirasoft.outcast.domain.repository.EpisodeRepository
import com.caldeirasoft.outcast.domain.models.EpisodeSummary
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class FetchEpisodesHistoryUseCase @Inject constructor(
    val episodeRepository: EpisodeRepository)
    : FlowUseCase<FetchEpisodesHistoryUseCase.Params, List<EpisodeSummary>> {
    override fun execute(param: Params) =
        when {
            param.podcastId != null -> episodeRepository.fetchEpisodesHistoryByPodcastId(param.podcastId)
            else -> episodeRepository.fetchEpisodesHistory()
        }

    data class Params(val podcastId: Long?)
}