from PIL import Image
import os
import numpy as np

directory_path = 'mnist_images'

# Function to filter filenames
def filter_filenames(filenames, suffixes):
    return [filename for filename in filenames if any(filename.endswith(suffix + ".png") for suffix in suffixes)]

# Get the list of filtered filenames
suffixes = ["_8", "_9"]
filtered_filenames = filter_filenames(os.listdir(directory_path), suffixes)

# Load images
images = []
for filename in filtered_filenames:
    img_path = os.path.join(directory_path, filename)
    images.append(Image.open(img_path))

# Pre-process images
preprocessed_images = [img.convert('L').resize((28, 28)) for img in images]

# Convert to numpy arrays and normalize
image_arrays = np.array([np.array(img) for img in preprocessed_images])
image_arrays = image_arrays.reshape(-1, 28, 28, 1) / 255.0  

# Assume 'model' is your trained model
predictions = model.predict(image_arrays)
predicted_digits = np.argmax(predictions, axis=1)

# Print filenames with their corresponding predictions
for filename, digit in zip(filtered_filenames, predicted_digits):
    print(f"Filename: {filename}, Predicted Digit: {digit}")
