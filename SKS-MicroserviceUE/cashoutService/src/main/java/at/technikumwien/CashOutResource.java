package at.technikumwien;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@CrossOrigin
@Log
public class CashOutResource {

    @Autowired
    private AuthorCashOutRepository authorCashOutRepository;
    private List<Author> paymentList;
    private Author author;

    //es fehlen nur noch Events um das auszulösen

    public void updatePayment(@PathVariable long id){
        log.info("Payment +1 >> id=" +id);
        author = authorCashOutRepository.findById(id)
                .orElseThrow(
                        () -> new EmptyResultDataAccessException("can't find news with id " + id, 1)
                );
        author.setId(id);
        author.setPayment(author.getPayment()+1);
        authorCashOutRepository.save(author);
    }

    public void cashOut(){
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("Europe/Vienna"));
        if(calendar.get(Calendar.DAY_OF_MONTH) == Calendar.getInstance().getActualMaximum(Calendar.DAY_OF_MONTH)){
            //update datenbank
            log.info("CashOut for this Month");
            paymentList = authorCashOutRepository.findAll();

            for(int i = 0; i < paymentList.size(); i++){
                author = paymentList.get(i);
                author.setId(author.getId());
                author.setPayment(0);
                authorCashOutRepository.save(author);
            }

        }
    }
}
