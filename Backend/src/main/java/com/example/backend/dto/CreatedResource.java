package com.example.backend.dto;

import java.net.URI;

public record CreatedResource<T>(T dto, URI location) {}
