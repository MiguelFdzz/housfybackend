package fdz.migue.housfybackend.service;

import fdz.migue.housfybackend.entity.IdeaVote;
import fdz.migue.housfybackend.entity.IdeaVoteId;
import fdz.migue.housfybackend.entity.Idea;
import fdz.migue.housfybackend.entity.User;
import fdz.migue.housfybackend.repository.IdeaVoteRepository;
import fdz.migue.housfybackend.repository.IdeaRepository;
import fdz.migue.housfybackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class IdeaVoteService {

    @Autowired
    private IdeaVoteRepository ideaVoteRepository;
    @Autowired
    private IdeaRepository ideaRepository;
    @Autowired
    private UserRepository userRepository;

    @Transactional(readOnly = true)
    public List<IdeaVote> findAll(){
        return ideaVoteRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<IdeaVote> findById(IdeaVoteId id){
        return ideaVoteRepository.findById(id);
    }

    @Transactional
    public IdeaVote create(IdeaVote ideaVote){
        Idea idea = ideaRepository.findById(ideaVote.getId().getIdeaId())
                .orElseThrow(() -> new RuntimeException("Idea not found with id " + ideaVote.getId().getIdeaId()));
        User user = userRepository.findById(ideaVote.getId().getUserId())
                .orElseThrow(() -> new RuntimeException("User not found with id " + ideaVote.getId().getUserId()));

        ideaVote.setIdea(idea);
        ideaVote.setUser(user);

        Optional<IdeaVote> existingVote = ideaVoteRepository.findById(ideaVote.getId());
        if (existingVote.isPresent()) {
            IdeaVote foundVote = existingVote.get();
            foundVote.setVoteType(ideaVote.getVoteType());
            foundVote.setCreationDate(Timestamp.from(Instant.now()));
            return ideaVoteRepository.save(foundVote);
        } else {
            return ideaVoteRepository.save(ideaVote);
        }
    }

    @Transactional
    public IdeaVote save(IdeaVote ideaVote){
        return ideaVoteRepository.save(ideaVote);
    }

    @Transactional
    public IdeaVote updateById(IdeaVoteId id, IdeaVote ideaVoteDetails){
        IdeaVote foundIdeaVote = ideaVoteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("IdeaVote for Idea ID " + id.getIdeaId() + " and User ID " + id.getUserId() + " not found"));

        foundIdeaVote.setVoteType(ideaVoteDetails.getVoteType());
        foundIdeaVote.setCreationDate(Timestamp.from(Instant.now()));

        return ideaVoteRepository.save(foundIdeaVote);
    }

    @Transactional
    public void delete(IdeaVoteId id){
        if (!ideaVoteRepository.existsById(id)) {
            throw new RuntimeException("IdeaVote for Idea ID " + id.getIdeaId() + " and User ID " + id.getUserId() + " not found");
        }
        ideaVoteRepository.deleteById(id);
    }
}