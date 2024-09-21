package com.al3x.lunarSkript.elements;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import com.lunarclient.apollo.Apollo;
import com.lunarclient.apollo.common.location.ApolloBlockLocation;
import com.lunarclient.apollo.module.beam.Beam;
import com.lunarclient.apollo.module.beam.BeamModule;
import com.lunarclient.apollo.player.ApolloPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

import static com.al3x.lunarSkript.ColorTranslator.getJavaColor;

public class showBeamWithId extends Effect {

    static {
        Skript.registerEffect(showBeamWithId.class,
                "show [lunar] beam for %player% [colored %string%] at %number% %number% %number% with id %string%"
        );
    }

    private BeamModule beamModule = Apollo.getModuleManager().getModule(BeamModule.class);

    private Expression<Player> player;
    private Expression<String> color;
    private Expression<Number> x;
    private Expression<Number> y;
    private Expression<Number> z;
    private Expression<String> id;

    @Override
    protected void execute(Event event) {
        Player target = player.getSingle(event);
        String beamColor = color.getSingle(event);
        Number xpos = x.getSingle(event);
        Number ypos = y.getSingle(event);
        Number zpos = z.getSingle(event);
        String beamId = id.getSingle(event);
        if (target == null) return;

        Optional<ApolloPlayer> apolloPlayerOpt = Apollo.getPlayerManager().getPlayer(target.getUniqueId());

        apolloPlayerOpt.ifPresent(apolloPlayer -> {
            beamModule.displayBeam(apolloPlayer, Beam.builder()
                    .id(beamId)
                    .color(getJavaColor(beamColor))
                    .location(ApolloBlockLocation.builder()
                            .world(target.getWorld().getName())
                            .x(xpos.intValue())
                            .y(ypos.intValue())
                            .z(zpos.intValue())
                            .build()
                    )
                    .build()
            );
        });
    }

    @Override
    public String toString(@Nullable Event event, boolean b) {
        return "Remove All Beams Effect with expression player " + player.toString(event, b);
    }

    @Override
    public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
        player = (Expression<Player>) expressions[0];
        color = (Expression<String>) (expressions[1] != null ? expressions[1] : "WHITE");
        x = (Expression<Number>) expressions[2];
        y = (Expression<Number>) expressions[3];
        z = (Expression<Number>) expressions[4];
        id = (Expression<String>) expressions[5];
        return true;
    }
}
