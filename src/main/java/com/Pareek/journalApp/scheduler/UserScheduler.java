package com.Pareek.journalApp.scheduler;

import com.Pareek.journalApp.Entity.JournalEntry;
import com.Pareek.journalApp.Entity.User;
import com.Pareek.journalApp.enums.Sentiment;
import com.Pareek.journalApp.repository.UserRepoImpl;
import com.Pareek.journalApp.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class UserScheduler {

    @Autowired
    private EmailService emailService;

    @Autowired
    private UserRepoImpl userRepo;

//    @Autowired
//    private sentimentAnalyse analyse;
    @Scheduled(cron="0 0 9 * * SUN")
    public void sendWeeklySentiments(){
        List<User> userList=userRepo.getUserWithEmail();
        for(User user:userList){
            List<JournalEntry> journalEntries=user.getEntries();
            List<Sentiment> filteredEntries= journalEntries.stream().filter(x -> x.getDate().isAfter(LocalDateTime.now().minus(7, ChronoUnit.DAYS))).map(x -> x.getSentiment()).collect(Collectors.toList());

            Map<Sentiment,Integer> sentiments=new HashMap<>();

            Sentiment mfs=null;
            int maxCount=0;

            for(Sentiment sentiment: filteredEntries){
                if(sentiment!=null){
                    sentiments.put(sentiment,sentiments.getOrDefault(sentiment,0)+1);

                    if(sentiments.get(sentiment) > maxCount){
                        maxCount=sentiments.get(sentiment);
                        mfs=sentiment;
                    }
                }
            }

            if(mfs!=null){
                emailService.sendEmail(user.getEmail(),"Sentiment", sentiments.toString());

            }

//            String sentiment=do;

        }
    }

}
