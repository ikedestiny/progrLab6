package org.example.common.data;


import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Coordinates implements Serializable {
    @Min(value = -285)
    private Long x; //Значение поля должно быть больше -286
    @Max(value = 703)
    @NotNull
    private Double y; //Максимальное значение поля: 703, Поле не может быть null

}
