package org.example.pokemon;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true) // 추가
public class Pokemon {
    private String name;
    private int id;
    private List<Type> types;
    private List<Ability> abilities;
    private int height;
    private int weight;
    private Sprites sprites;
    public List<Stat> stats;

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public int getHeight() {
        return height;
    }

    public int getWeight() {
        return weight;
    }

    public List<Type> getTypes() {
        return types;
    }

    public List<Ability> getAbilities() {
        return abilities;
    }

    public List<Stat> getStats() {
        return stats;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Type {
        private TypeDetails type;

        public TypeDetails getType() {
            return type;
        }
    }

    public Sprites getSprites() {
        return sprites;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class TypeDetails {
        private String name;

        public String getName() {
            return name;
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Ability {
        private AbilityDetails ability;

        public AbilityDetails getAbility() {
            return ability;
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class AbilityDetails {
        private String name;

        public String getName() {
            return name;
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Sprites {
        @JsonProperty("front_default")
        private String frontDefault;

        public String getFrontDefault() {
            return frontDefault;
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Stat {
        private StatDetails stat;
        @JsonProperty("base_stat")
        private int baseStat;

        public StatDetails getStat() {
            return stat;
        }

        public int getBaseStat() {
            return baseStat;
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class StatDetails {
        private String name;

        public String getName() {
            return name;
        }
    }
}
