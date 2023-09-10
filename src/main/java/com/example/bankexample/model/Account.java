package com.example.bankexample.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "accounts")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    private int type;

    @Column(name = "status")
    private int status;

    @Column(name = "balance")
    private BigDecimal balance;

    @Column(name = "currency_code")
    private int currencyCode;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    private Client client;

    @OneToOne(
            fetch = FetchType.LAZY,
            mappedBy = "account"
    )
    private Agreement agreement;

    @OneToMany(
            mappedBy = "debitAccount",
            cascade = CascadeType.ALL
    )
    private Set<Transaction> debitTransactions = new HashSet<>();

    @OneToMany(
            mappedBy = "creditAccount",
            cascade = CascadeType.ALL
    )
    private Set<Transaction> creditTransactions = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(id, account.id) && Objects.equals(name, account.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
