package at.technikumwien;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

@CrossOrigin
@Log
public class CashOutRessource {

    @Autowired
    private CashOutRepository authorCashOutRepository;
    private List<CashOutAuthor> paymentList;
    @Autowired
    private CashOutAuthor author;

    public void updatePayment(long id){

        System.out.println(authorCashOutRepository.findAll());

        log.info("Payment +1Cent >> id=" + id);

        author = authorCashOutRepository.findById(id)
                .orElseThrow(
                        () -> new EmptyResultDataAccessException("can't find news with id " + id, 1)
                );
        author.setId(id);
        author.setPayment(author.getPayment()+0.01);
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
