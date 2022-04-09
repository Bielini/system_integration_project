package pl.bie.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Voivodeship {

    private String sourceFile;
    private String name;
    private Map<String,Double> valueByYears ;



}
