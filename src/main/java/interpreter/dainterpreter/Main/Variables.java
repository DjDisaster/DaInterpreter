package interpreter.dainterpreter.Main;

import org.bukkit.Bukkit;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.TreeMap;

public class Variables {
    public static HashMap<String, Object> variables = new HashMap<>();
    public static void AddVariable(String name, String value) {
        variables.put(name, value);
    }
    public static Object GetVariable(String name) {
        return variables.get(name);
    }
    // add
    public static void Add(String name, Double value) {
        Double variable = (Double) GetVariable(name);
        if (variable == null) {variable = (double) 0;}
        variable += value;
        variables.put(name, variable);
        Bukkit.broadcastMessage("All Variables" + variables.toString());
    }
}
//broadcast ran
        //System.out.println("Ran Add");
         //       double i = (double) GetVariable(name);
         //       // check if i is infinity
         //       // if i = null then set i to 0
         //       if (i == Double.POSITIVE_INFINITY) {
         //       BigInteger i2 = (BigInteger) GetVariable(name);
         //       // check if i2 is null
          //      if (i2 == null) {
          //      i2 = BigInteger.ZERO;
          //      }
          //      i2 = i2.add((BigInteger) value);
          //      variables.put(name, i2);
           //     } else {
           //     i += Double.parseDouble((String) value);
           //     variables.put(name, i);
           //     }
