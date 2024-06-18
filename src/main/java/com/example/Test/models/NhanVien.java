package com.example.Test.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Data
@Getter @Setter
@RequiredArgsConstructor @AllArgsConstructor
@Table(name = "NhanVien")
public class NhanVien {

    @Id
    @NotNull(message = "Mã nhân viên không được để trống")
    @Size(max = 3, message = "Mã nhân viên không được quá 3 ký tự")
    @Column(length = 3, nullable = false)
    private String Ma_NV;

    @Column(name = "Ten_NV", length=100, nullable = false)
    @Size(max = 100, message = "Tên nhân viên không được quá 100 ký tự")
    private String Ten_NV;

    @Column(name = "Phai",length =3)
    @Size(max = 3, message = "Giới tính không được quá 3 ký tự")
    @NotBlank(message = "Giới tính không được để trống")
    private String Phai;

    @Column(name = "Noi_Sinh", length=200)
    @Size(max = 200, message = "Nơi sinh không được quá 200 ký tự")
    @NotBlank(message = "Nơi sinh không được để trống")
    private String Noi_Sinh;

    @ManyToOne
    @JoinColumn(name = "Ma_Phong")
    private PhongBan phongBan;

    @Column(name = "Luong")
    @Positive(message = "Lương phải là số dương")
    private long Luong;
}
