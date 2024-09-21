package com.al3x.lunarSkript.elements;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import com.lunarclient.apollo.Apollo;
import com.lunarclient.apollo.module.beam.BeamModule;
import com.lunarclient.apollo.player.ApolloPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class removeAllBeams extends Effect {

    static {
        Skript.registerEffect(removeAllBeams.class,
                "remove all [lunar] beam[s] for %player%"
        );
    }

    private BeamModule beamModule = Apollo.getModuleManager().getModule(BeamModule.class);

    private Expression<Player> player;

    @Override
    protected void execute(Event event) {
        Player target = player.getSingle(event);
        if (target == null) return;

        Optional<ApolloPlayer> apolloPlayerOpt = Apollo.getPlayerManager().getPlayer(target.getUniqueId());
        apolloPlayerOpt.ifPresent(beamModule::resetBeams);
    }

    @Override
    public String toString(@Nullable Event event, boolean b) {
        return "Remove All Beams Effect with expression player " + player.toString(event, b);
    }

    @Override
    public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
        player = (Expression<Player>) expressions[0];
        return true;
    }
}
