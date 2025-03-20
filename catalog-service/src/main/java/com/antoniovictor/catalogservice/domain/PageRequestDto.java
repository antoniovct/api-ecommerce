package com.antoniovictor.catalogservice.domain;

public record PageRequestDto(
         Integer pageNumber,
         Integer pageSize
) {

}
