#ifndef _IMAGE
#define _IMAGE
#include <iostream>
#include "Vec3.h"
#include <string>
#include "Array.h"
#include "ppmLib/ppm.h"
using namespace std;
using namespace imaging;
/*! The imaging namespace contains every class or function associated with the image storage, compression and manipulation.
 */
namespace math
{
	typedef Vec3<float> Color;
	class Image : public Array<Color>
	{
	public:
		//load image
		bool load(const string & filename, const string & format) {
			if (format.compare("ppm") != 0) {
				cout << "Wrong format" << endl;
				return false;
			}
			int w = 0;
			int h = 0;
			float* colors = ReadPPM(filename.c_str(), &w, &h);
			if (colors == nullptr) {
				return false;
			}
			width = w;
			height = h;

			for (unsigned int i = 0; i <= 3 * getWidth()*getHeight() - 3; i += 3) {
				Vec3<float> ok;
				ok.r = colors[i];
				ok.g = colors[i+1];
				ok.b = colors[i + 2];
				buffer.push_back(ok);
			}
			delete[]colors;
			return true;
		};
		//save image
		bool save(const std::string & filename, const std::string & format) {
			if (format.compare("ppm") != 0) {
				cout << "Wrong format" << endl;
				return false;
			}
			if(buffer.empty()) { 
				return false; 
			}
			float* floatArray = new float[3 * getWidth()*getHeight()];
			int f = -1;
			for (unsigned int i = 0; i <= 3 * getWidth()*getHeight() - 3; i += 3) {
				f++;
				for (unsigned int j = 0; j < 3; j++) {
					floatArray[i + j] = (float)buffer.at(f)[j];
				}
			}
			return imaging::WritePPM(floatArray, Image::getWidth(), Image::getHeight(), filename.c_str());

		};
		~Image(){}
		//constuctors
		Image():Array<Color>(){}
		Image(const Image&image) :Array <Color>(image) {}

	};
} //namespace math
#endif