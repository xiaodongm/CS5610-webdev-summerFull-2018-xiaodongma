package webdev.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import webdev.models.Topic;
import webdev.models.Widget;
import webdev.repositories.TopicRepository;
import webdev.repositories.WidgetRepository;

@RestController
@CrossOrigin(origins = "*")
public class WidgetService {
	@Autowired
	WidgetRepository widgetRepository;
	
	@Autowired
	TopicRepository topicRepository;
	
	@GetMapping("/api/topic/{topicId}/widget")
	public List<Widget> findAllWidgetsForTopic(@PathVariable("topicId") int topicId) {
		Optional<Topic> data = topicRepository.findById(topicId);
		if(data.isPresent()) {
			Topic topic = data.get();
			return topic.getWidgets();
		}
		return null;
	}
	
	
	@GetMapping("/api/widget")
	public List<Widget> findAllWidgets() {
		return (List<Widget>) widgetRepository.findAll();
	}
	
	
	@PostMapping("/api/topic/{tid}/widget")
	public void saveAllWidgets(@PathVariable("tid") int topicId, @RequestBody List<Widget> newWidgets) {
		
		Optional<Topic> data = topicRepository.findById(topicId);
		
		if (data.isPresent()) {
			List<Widget> widgets = findAllWidgetsForTopic(topicId);
			for(Widget widget : widgets) {
				widgetRepository.delete(widget);
			}
			Topic topic = data.get();
			for(Widget widget : newWidgets) {
				widget.setTopic(topic);
				widgetRepository.save(widget);
			}
		}
	}
	
	
	
}
