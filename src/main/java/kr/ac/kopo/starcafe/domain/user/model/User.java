package kr.ac.kopo.starcafe.domain.user.model;

import kr.ac.kopo.starcafe.domain.cart.model.Cart;
import kr.ac.kopo.starcafe.domain.user.model.dto.Address;
import kr.ac.kopo.starcafe.domain.user.model.dto.ModifiedUserRequest;
import kr.ac.kopo.starcafe.domain.user.model.dto.RoleType;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    private String email;

    private String password;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Embedded
    private Address address;

    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id")
    private Cart cart;

    private boolean activated;

    /**
     * 비즈니스 로직
     */

    public void setEncodedPassword(String encodedPassword) {
        this.password = encodedPassword;
    }

    public void modified(final ModifiedUserRequest request) {
        this.firstName = request.getFirstName();
        this.lastName = request.getLastName();
        this.address = request.getAddress();
    }

    public void expiredAccount() {
        this.activated = false;
    }
}
