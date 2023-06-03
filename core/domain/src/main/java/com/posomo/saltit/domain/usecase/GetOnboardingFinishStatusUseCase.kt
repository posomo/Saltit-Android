package com.posomo.saltit.domain.usecase

import com.posomo.saltit.data.repository.SaltitRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetOnboardingFinishStatusUseCase @Inject constructor(
	private val saltitRepository: SaltitRepository
) {

	operator fun invoke() = flow {
		saltitRepository.getOnboardingFinishStatus().collect { isFinished ->
			emit(isFinished)
		}
	}
}