package com.posomo.saltit.data.mapper

interface ObjectMapper<Domain, Data> {

	fun asDomain(data: Data): Domain

	fun asData(domain: Domain): Data
}