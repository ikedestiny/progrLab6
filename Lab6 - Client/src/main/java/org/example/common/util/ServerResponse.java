package org.example.common.util;


import lombok.AllArgsConstructor;
import lombok.Data;
import org.example.common.data.SpaceMarine;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;

@Data
@AllArgsConstructor
public class ServerResponse implements Serializable {
    @Serial
    private static final long serialVersionUID = 4456406162521317037L;
    private Status status;
    private String message;
    private ArrayList<SpaceMarine> marines;


}
