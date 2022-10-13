package interpreter.dainterpreter.Effects;

import interpreter.dainterpreter.Main.Execute;

public class EffectRegister {
    public static void register(String syntax, String[] args) {

        Execute.effects.put(syntax, args.toString()
        );
    }
}
