#ifndef _FILTERGAMMA
#define _FILTERGAMMA
#include "Filter.h"
class FilterGamma : public Filter {
	float c;
	public:
		//constuctors
		FilterGamma() {
			c = 0.5;
		}
		FilterGamma( float c) : c(c) { }
		FilterGamma(const FilterGamma &f) : c(f.c) {}
		//operator
		Image operator << (const Image & image) {
			if (c > 2) c = 2;
			if (c < 0.5) c = 0.5;
			Image fimage(image);

			for (unsigned int x = 0; x < image.getWidth(); x++) {
				for (unsigned int y = 0; y < image.getHeight(); y++) {
					Color color = fimage.getT(x, y);
					color.x = powf(color.x, c);
					color.y = powf(color.y, c);
					color.z = powf(color.z, c);
					color=color.clampToLowerBound(0);
					color=color.clampToUpperBound(1);
					fimage.setT(x, y, color);
				}
			}
			return fimage;
		}
};
#endif