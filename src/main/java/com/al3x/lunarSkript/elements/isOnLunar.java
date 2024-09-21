package com.al3x.lunarSkript.elements;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Condition;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import com.lunarclient.apollo.Apollo;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import javax.annotation.Nullable;

public class isOnLunar extends Condition {

    static {
        Skript.registerCondition(isOnLunar.class, "%player% (1¦is|2¦is(n't| not)) on lunar[client]");
    }

    private Expression<Player> player;

    @Override
    public String toString(@Nullable Event event, boolean b) {
        return "IsOnLunar expression with expression player " + player.toString(event, b);
    }

    @Override
    public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
        player = (Expression<Player>) expressions[0];
        return true;
    }

    @Override
    public boolean check(Event event) {
        Player p = player.getSingle(event);
        if (p != null) {
            return Apollo.getPlayerManager().hasSupport(p.getUniqueId());
        }
        return false;
    }
}
