package com.example.Test.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;
import java.util.Set;

@Data
@Getter @Setter
@RequiredArgsConstructor @AllArgsConstructor
@Entity
@Table(name = "Phong_Ban")
public class PhongBan {
    @Id
    @Column(length=2, nullable = false)
    @Size(max=2,message = "Mã phòng chỉ được điền tối đa 2 ký tự")
    private String Ma_Phong;

    @Column(length = 30,nullable = false)
    @Size(max = 30, message = "Tên phòng được dưới 30 ký tự")
    private String Ten_Phong;

}
