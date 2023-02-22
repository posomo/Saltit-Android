package com.posomo.saltit.common_ui.recyclerview

sealed interface VHComponent {
	object SearchHeaderCp : VHComponent {
		object AREA : VHComponent
	}
	object SaltitPickStoreCp : VHComponent {
		object FILTER : VHComponent
		object REFRESH : VHComponent
	}

	object DEFAULT : VHComponent
}