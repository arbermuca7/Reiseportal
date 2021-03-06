package at.technikumwien;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

@EnableScheduling
@Component
@Log
public class CashOutRessource {

    @Autowired
    private CashOutRepository authorCashOutRepository;
    private CashOutAuthor author;

    private List<CashOutAuthor> paymentList;

    public void updatePayment(long id){

        log.info("Payment +1Cent >> id=" + id);
        author = authorCashOutRepository.findById(id)
                .orElseThrow(
                        () -> new EmptyResultDataAccessException("can't find news with id " + id, 1)
                );
        author.setId(id);
        author.setPayment(author.getPayment()+0.01);
        authorCashOutRepository.save(author);

    }

    //0:00 jeden Tag
    @Scheduled(cron = "0 0 0 * * *")
    public void cashOut(){
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("Europe/Vienna"));
        if(calendar.get(Calendar.DAY_OF_MONTH) == Calendar.getInstance().getActualMaximum(Calendar.DAY_OF_MONTH)){
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
