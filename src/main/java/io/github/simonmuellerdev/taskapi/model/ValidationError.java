package io.github.simonmuellerdev.taskapi.model;

public record ValidationError (String field, String message) {
}
