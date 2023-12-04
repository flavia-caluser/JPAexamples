package com.springapps.jpaexamples.twitterapp;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    UserRepository userRepository;
    TweetRepository tweetRepository;

    CommentRepository commentRepository;

    @Autowired
    public UserService(UserRepository userRepository, TweetRepository tweetRepository, CommentRepository commentRepository) {
        this.userRepository = userRepository;
        this.tweetRepository = tweetRepository;
        this.commentRepository = commentRepository;
    }

    public User saveUser(User user){
        return userRepository.save(user);
    }

    @Transactional
    public User addTweetToUser1(Long userId, Tweet tweet){
        User user = userRepository.findById(userId).orElseThrow(()->new EntityNotFoundException("nu s-a gasit id-ul user-ului"));
        tweet.setUser(user); //asigur legatura intre tweet si user pt baza de date
        user.getTweets().add(tweet);  //asigur si legatura in java
        return userRepository.save(user); //salvez userul si cu ajutorul CascadeALL imi va salva si tweet-urile pt ca sunt relatie one-to-many

    }
    @Transactional
    public Tweet addTweetToUser2(Long userId, Tweet tweet){
        User user = userRepository.findById(userId).orElseThrow(()->new EntityNotFoundException("nu s-a gasit id-ul user-ului")); // aici imi cauta si imi gaseste userul n baza de date
        tweet.setUser(user); //asigur legatura intre tweet si user pt baza de date

      //  user.getTweets().add(tweet); //imi adauga si in java la lista de tweet-uri a userului
        // Tweet dbTweet = tweetRepository.save(tweet); // salvez pt baza de date tweetul
        //List<Tweet> tweets = user.getTweets(); //accesez din java lista de tweeturi actualizata din java

        return tweetRepository.save(tweet);
    }

    @Transactional
    public void deleteAllTweetsByUser(Long userId){
//        User user = userRepository.findById(userId).orElseThrow(()->new EntityNotFoundException("nu s-a gasit id-ul user-ului"));
//        tweetRepository.deleteAll(user.getTweets());
        tweetRepository.deleteAllByUser_Id(userId);

    }
    @Transactional
    public User deleteAllTweetsByUser2(Long userId){
        User user = userRepository.findById(userId).orElseThrow(()->new EntityNotFoundException("nu s-a gasit id-ul user-ului"));
        user.getTweets().clear();
        return userRepository.save(user); //stergang toate tweeturile unui user si apoi salvand, nu se sterg si tweet-urile pe baza de date, dar prin orphane true stie intelij sa ii stearga automat daca se face detasarea.
    }

    @Transactional
    public void deleteUser(long userId){
        User user = userRepository.findById(userId).orElseThrow(()->new EntityNotFoundException("nu s-a gasit id-ul user-ului"));
        userRepository.delete(user);
    }

    public List<Tweet> findAllTweetsByUser(Long userId){
        return tweetRepository.findAllByUser_Id(userId);
    }

    @Transactional
    public List<Comment> findAlCommentsByUser2(Long userId) throws Exception {
        //pasul 1- obtinem lista de tweet-uri a utilizatorului cu userId

        //var 1 - apelam metoda de mai sus, care foloseste un derived query declarat de noi in repository
        List<Tweet> tweets = findAllTweetsByUser(userId);

        //var 2 - cautam userul si prin lazy loading ii accesam tweet-urile
        //User user = userRepository.findById(userId).orElseThrow(() -> new Exception("user not found"));
        //user.getTweets();



        //pasul 2 - procesam tweet-urile gasite astfel incat sa obtinem o lista de comentarii adunata de la fiecare tweet

        //var1 - java 8 - stream pe tweets si flatMap
        return tweets.stream()
                .flatMap(t -> t.getComments().stream())
                .collect(Collectors.toList());

        //var 2 - parcurgere clasica si adaugarea elementelor intr-un array result
//        List<Comment> result = new ArrayList<>();
//        for (Tweet tweet: tweets) {
//            result.addAll(tweet.getComments());
//        }
//        return result;
    }

    @Transactional
    public List<Comment> findAlCommentsByUser(Long userId) throws Exception {
        return commentRepository.findAllByTweet_User_Id(userId);
    }

    @Transactional
    public List<Comment> findAlCommentsByUser3(Long userId) throws Exception {
        List<Comment> comments = commentRepository.findAll();
//        List<Comment> result = new ArrayList<>();
//        for (Comment comment: comments){
//            if (comment.getTweet().getUser().getId().equals(userId) ){
//                result.add(comment);
//            }
//        }
//        return result;

        return comments.stream()
                .filter(c -> c.getTweet().getUser().getId().equals(userId))
                .collect(Collectors.toList());
    }
}
