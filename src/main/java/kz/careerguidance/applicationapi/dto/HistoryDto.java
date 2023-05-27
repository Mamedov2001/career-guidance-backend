package kz.careerguidance.applicationapi.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class HistoryDto {

    private Long id;

    @NotNull(message = "Name is required")
    private String name;

    private String result;

}
