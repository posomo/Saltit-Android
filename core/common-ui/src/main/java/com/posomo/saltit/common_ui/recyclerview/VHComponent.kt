package com.posomo.saltit.common_ui.recyclerview

sealed interface VHComponent {
	sealed class SearchHeaderCp : VHComponent {
		object AREA : VHComponent
	}
	sealed class SaltitPickStoreCp : VHComponent {
		object FILTER : VHComponent
		object REFRESH : VHComponent
	}

	sealed class DEFAULT : VHComponent
}