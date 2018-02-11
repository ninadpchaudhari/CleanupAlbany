package com.exmaple.misc;

//Imports the Google Cloud client library
/*
 * google.cloud.vision.v1.EntityAnnotation.mid : /m/07s6nbt
google.cloud.vision.v1.EntityAnnotation.description : text
google.cloud.vision.v1.EntityAnnotation.score : 0.9317424
google.cloud.vision.v1.EntityAnnotation.topicality : 0.9317424
google.cloud.vision.v1.EntityAnnotation.mid : /m/02y3rj
google.cloud.vision.v1.EntityAnnotation.description : presentation
google.cloud.vision.v1.EntityAnnotation.score : 0.70614463
google.cloud.vision.v1.EntityAnnotation.topicality : 0.70614463
google.cloud.vision.v1.EntityAnnotation.mid : /m/03qh03g
google.cloud.vision.v1.EntityAnnotation.description : media
google.cloud.vision.v1.EntityAnnotation.score : 0.67801243
google.cloud.vision.v1.EntityAnnotation.topicality : 0.67801243
google.cloud.vision.v1.EntityAnnotation.mid : /m/01jwgf
google.cloud.vision.v1.EntityAnnotation.description : product
google.cloud.vision.v1.EntityAnnotation.score : 0.62960494
google.cloud.vision.v1.EntityAnnotation.topicality : 0.62960494
google.cloud.vision.v1.EntityAnnotation.mid : /m/03gq5hm
google.cloud.vision.v1.EntityAnnotation.description : font
google.cloud.vision.v1.EntityAnnotation.score : 0.61611974
google.cloud.vision.v1.EntityAnnotation.topicality : 0.61611974
google.cloud.vision.v1.EntityAnnotation.mid : /m/01h8n0
google.cloud.vision.v1.EntityAnnotation.description : conversation
google.cloud.vision.v1.EntityAnnotation.score : 0.60719246
google.cloud.vision.v1.EntityAnnotation.topicality : 0.60719246
google.cloud.vision.v1.EntityAnnotation.mid : /m/01cd9
google.cloud.vision.v1.EntityAnnotation.description : brand
google.cloud.vision.v1.EntityAnnotation.score : 0.55127954
google.cloud.vision.v1.EntityAnnotation.topicality : 0.55127954
google.cloud.vision.v1.EntityAnnotation.mid : /m/062zr
google.cloud.vision.v1.EntityAnnotation.description : public relations
google.cloud.vision.v1.EntityAnnotation.score : 0.53798157
google.cloud.vision.v1.EntityAnnotation.topicality : 0.53798157
google.cloud.vision.v1.EntityAnnotation.mid : /m/01lhf
google.cloud.vision.v1.EntityAnnotation.description : communication
google.cloud.vision.v1.EntityAnnotation.score : 0.52389
google.cloud.vision.v1.EntityAnnotation.topicality : 0.52389
 */
import com.google.cloud.vision.v1.AnnotateImageRequest;
import com.google.cloud.vision.v1.AnnotateImageResponse;
import com.google.cloud.vision.v1.BatchAnnotateImagesResponse;
import com.google.cloud.vision.v1.EntityAnnotation;
import com.google.cloud.vision.v1.Feature;
import com.google.cloud.vision.v1.Feature.Type;
import com.google.cloud.vision.v1.Image;
import com.google.cloud.vision.v1.ImageAnnotatorClient;
import com.google.protobuf.ByteString;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GoogleVision {
	public static HashMap<String,String> getImageContent(long issueId) throws Exception {
		HashMap<String,String> descriptionScore = new HashMap<String,String>();
		// Instantiates a client
		try (ImageAnnotatorClient vision = ImageAnnotatorClient.create()) {
			Path resourceDirectory = Paths.get("src/main/webapp/resources");
			// The path to the image file to annotate
			String fileName = resourceDirectory.toAbsolutePath().toString() + '/' + "issue_"+issueId + ".png";

			// Reads the image file into memory
			Path path = Paths.get(fileName);
			byte[] data = Files.readAllBytes(path);
			ByteString imgBytes = ByteString.copyFrom(data);

			// Builds the image annotation request
			List<AnnotateImageRequest> requests = new ArrayList<>();
			Image img = Image.newBuilder().setContent(imgBytes).build();
			Feature feat = Feature.newBuilder().setType(Type.LABEL_DETECTION).build();
			AnnotateImageRequest request = AnnotateImageRequest.newBuilder().addFeatures(feat).setImage(img).build();
			requests.add(request);

			// Performs label detection on the image file
			BatchAnnotateImagesResponse response = vision.batchAnnotateImages(requests);
			List<AnnotateImageResponse> responses = response.getResponsesList();

			for (AnnotateImageResponse res : responses) {
				if (res.hasError()) {
					System.out.printf("Error: %s\n", res.getError().getMessage());
					return null;
				}

				for (EntityAnnotation annotation : res.getLabelAnnotationsList()) {
					
					annotation.getAllFields().forEach((k, v) -> descriptionScore.put(k.toString(), v.toString()));
				}
			}
		}
		return descriptionScore;
	}
	
}