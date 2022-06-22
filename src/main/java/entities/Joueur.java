package entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
//@AllArgsConstructor
public class Joueur {

    String name;
    String pointAct;
    int setAct;
    List<String> points = new ArrayList<>();
    List<Integer> set = new ArrayList<>();
}
