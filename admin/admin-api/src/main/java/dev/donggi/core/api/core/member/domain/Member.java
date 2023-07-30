package dev.donggi.core.api.core.member.domain;

import dev.donggi.core.api.core.member.exception.MemberInvalidPasswordException;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "members")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String memberId;
    private String password;

    public void checkPassword(String password) {
        if (!this.password.equals(password)) {
            throw new MemberInvalidPasswordException();
        }
    }
}
