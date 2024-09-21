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

public class removeBeamWithId extends Effect {

    static {
        Skript.registerEffect(removeBeamWithId.class,
                "remove [lunar] beam for %player% with [id] %string%"
        );
    }

    private BeamModule beamModule = Apollo.getModuleManager().getModule(BeamModule.class);

    private Expression<Player> player;
    private Expression<String> id;

    @Override
    protected void execute(Event event) {
        Player target = player.getSingle(event);
        String beamId = id.getSingle(event);
        if (target == null) return;

        Optional<ApolloPlayer> apolloPlayerOpt = Apollo.getPlayerManager().getPlayer(target.getUniqueId());
        apolloPlayerOpt.ifPresent(apolloPlayer -> beamModule.removeBeam(apolloPlayer, beamId));
    }

    @Override
    public String toString(@Nullable Event event, boolean b) {
        return "RemoveBeamWithID Effect with expression player " + player.toString(event, b) + " and ID " + id.toString(event, b);
    }

    @Override
    public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
        player = (Expression<Player>) expressions[0];
        id = (Expression<String>) expressions[1];
        return true;
    }
}
