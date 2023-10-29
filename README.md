# Hockey Tournament

### :ice_hockey: IIHF '24 World Championship Fun Game

* Add games
* Track games on a dashboard

### Key development principals

* Have a fun :sunglasses:
* Keep it simple, stupid ;)
* Discover Java 21 features
    * Immutable Set :x:
    * Unmodifiable collections :white_check_mark:
    * The Not Predicate Method :x:
    * Local-Variable Syntax for Lambda :x:
    * Text Blocks :x:
    * Switch expressions :x:
    * Records :x:
    * Sealed Classes :x:
* Learn something new :white_check_mark:

```java
public record Game(String home, String away) {
}
```

```java
boolean canBeFinished=switch(state){
        case"IN_PROGRES"->true;
        case"SCHEDULED"->false;
default ->throw new IllegalArgumentException();
        }
```

```java
String summary="""
    Team 0 - Team 1
"""
```

```java
List<Game> positiveTotalScore=games.stream()
        .filter(Predicate.not(Game::isPositiveTotalScore))
        .collect(Collectors.toList())
```

```java
List<String> upperHomeTeams=games.stream()
        .map((@Nonnull var game)->game.getHomeTeam().toUpperCase())
        .collect(Collectors.toList())
```

