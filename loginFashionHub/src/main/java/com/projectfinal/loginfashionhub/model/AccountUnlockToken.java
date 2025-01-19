package com.projectfinal.loginfashionhub.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Represents a token used to unlock a user's account.
 * This token is generated when a user requests to unlock their account.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "account_unlock_tokens")
public class AccountUnlockToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Unique identifier for each token

    private String token; // Token used to unlock the account

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private User user; // User associated with this token

    private Date expiryDate; // Expiry date of the token

    private boolean used; // Indicates whether the token has been used
}
