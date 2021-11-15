package com.example.springbootclasshw1.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookInventory {
    private int id; //số tự động tăng
    private String bookId;
    private int amount; //số sách còn lại trong kho
    private LocalDateTime updateDate; //ngày, giờ cập nhật lại
}
