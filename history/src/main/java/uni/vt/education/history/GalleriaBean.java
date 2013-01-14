package uni.vt.education.history;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@SessionScoped
@ManagedBean
public class GalleriaBean implements Serializable {

	private List<String> images;

	@PostConstruct
	public void init() {
		images = new ArrayList<String>();
		for (int i = 1; i <= 12; i++) {
			images.add("galleria" + i + ".jpg");
		}
	}

	public List<String> getImages() {
		return images;
	}
}