package application.repository;

import application.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long> {
    List<Supplier> findByNameContainingAndAddressContainingAndProductsNameContaining(String name, String address, String productName);

    /**
     * vì sao lại có câu lênh (or p is null) trong câu lệnh query:
     * bối cảnh: ví dụ nếu bạn muốn query tất cả các supplier thì bạn sẽ để mọi tham số là rỗng đúng không ?
     * kết quả sẽ thật bất ngờ là chỉ có những supp có sản phẩm thì mới được lấy ra mà không phải là toàn bộ supp như mong muốn
     * bạn sử dụng câu lệnh sinh ra bởi console của hibernate để query thử thì hoàn toàn ra toàn bộ các supp
     * lý do vì sao: rất đơn giản vì @query là jpql và nó sẽ làm việc với entity và không phải là trực tiếp trên bảng
     * phân tích kỹ thì ta có thể thấy với native query thì: 'productname is null' sẽ luôn sai nếu như ta luôn gửi về 1 productname bao gồm cả 1 chuỗi rỗng
     *                                                       'productname = '' sẽ luôn đúng khi ta muốn truy vấn toàn bộ các sản phẩm của 1 supp nào đó
     *                                                       mấu chốt ở đây chính là native query thì p.name sẽ đúng với mọi điều kiện
           nhưng với jpql thì khác nhé: ở đây supp nào mà không có prod thì coi như cả bản ghi đó là sai
            mặc dù đã điều kiện thứ nhất và thứ hai đúng thì cả biểu thức đã coi như là đúng rồi nhưng
            jpql lại có 1 cái chết dở là p.name like ... không thể thực hiện được vì p là null đối với supp này
            thì coi như cả bản ghi đó là sai luôn, để lấy được những supp mà không có prod nào thì ta phải thêm
            or p is null ở cuối của câu lệnh where để đảm bảo rằng kể cả khi prod là null thì vẫn sẽ được chấp nhận


     update !!!!!
     hờ :>> : mắc công giải thích ghê, câu query này sai mịa rồi
     câu query này có ý nghĩa là nếu mà ông nào không có prod thì ông đó thỏa mãn ??
     là như kiểu nếu mà tôi muốn tìm 1 ông tên Linh thì sẽ bị trộn vào kết quả ông Linh đấy là những ông không có prod
     ---- chết tiệt !!!!

     vì thế mà câu query này sẽ chỉ trả về những ông có sản phẩm thôi nhé
     nhưng vì sao nó chỉ trả lại những bản ghi mà prod không null thì đã được giải thích ở trên rồi nhé (p.name like ....)

     * @param name
     * @param address
     * @param productName
     * @return
     */
//    @Query(value = "select s from Supplier s left join s.products p where " +
//            "s.name like %:name% and " +
//            "s.address like %:address% and " +
//            "(:productName is null or :productName = '' or p.name like %:productName%) or p is null") đây là câu lệnh bị sai
    @Query(value = "select s from Supplier s left join s.products p where " +
            "s.name like %:name% and " +
            "s.address like %:address% and " +
            "(:productName is null or :productName = '' or p.name like %:productName%)")
    List<Supplier> findSuppliers(@Param(value = "name") String name,
                                 @Param(value = "address") String address,
                                 @Param(value = "productName") String productName);
}
