package com.posomo.saltit.common_ui.recyclerview

sealed class VHComponent {
	object SearchHeaderCp : VHComponent() {
		object AREA : VHComponent()
	}
	object SaltitPickStoreCp : VHComponent() {
		object FILTER : VHComponent()
		object REFRESH : VHComponent()
	}

	object DEFAULT : VHComponent()
}