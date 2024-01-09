package game;

/**
 * Use this enum class to give `buff` or `debuff`.
 * It is also useful to give a `state` to abilities or actions that can be attached-detached.
 */
public enum Status {
    HOSTILE_TO_ENEMY, // use this status to be considered hostile towards enemy (e.g., to be attacked by enemy)
    DESTROY_SHELL,   // use this status to be considered being able to destroy Koopa's shell
    NOT_DORMANT,     // use this status for Koopa to be considered as not entered dormant state
    DORMANT,        // use this status for Koopa to be considered as entered dormant state
    SUPER,          // use this status to be considered having the SUPER effect after consuming Super Mushroom
    INVINCIBLE,     // use this status to be considered having the INVINCIBLE effect after consuming Power Star
    RESET,          // use this status to indicate that the resettable objects are ready to be reset
    VICTORY,        // use this status to indicate that Player has won
    FIRE_ATTACK,    // use this status to indicate the actor has fire attack capability
    FLY,            // use this status to indicate the actor has flying capability
}
