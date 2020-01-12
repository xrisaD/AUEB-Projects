#ifndef _ARRAY
#define _ARRAY
#include <vector>
#include "ppmLib/ppm.h"
using namespace std;
namespace math
{
	template <typename T>
	class Array {
	private: void Replicate(const Array<T> & right) { //usage:copy constructor and operator =
		width = right.width;
		height = right.height;
		for (auto it = right.buffer.begin(); it != right.buffer.end(); ++it) {
			buffer.push_back(*it);
		}
	}
	protected:						//! Holds the array data.
		unsigned int width, 		//! The width of the array
			height;
		vector<T> buffer;//! The height of the array
	public:
		// metric accessors

		/*! Returns the width of the array
		 */
		const unsigned int getWidth() const { return width; }

		/*! Returns the height of the array
		 */
		const unsigned int getHeight() const { return height; }

		// data accessors
		vector<T> * getRawDataPtr() {
			return buffer;
		};

		T getT(unsigned int x, unsigned int y) const  { //get data for buffer at (x,y)
			if (y> getHeight() || x > getWidth() || y<0 ||x<0) {
				return T();
			}
			else {
				return buffer[y*getWidth() + x];
			}
		};

		void setT(unsigned int x, unsigned int y, T & value) { //set value to buffer at (x,y)
			if (y > getHeight() || x > getWidth() || y < 0 || x < 0) {
				return;
			}
			else {
				buffer[y *getWidth() + x] = value;
			}
		};

		void setData(const vector<T> * & data_ptr) { //set buffer to buffer at (x,y)
			if (data_ptr == nullptr || data_ptr.size() == 0) {
				return;
			}
			for (unsigned int i = 0; i < sizeof(data_ptr) / sizeof(*data_ptr); i++) {
				buffer[i] = (*data_ptr)[i];
			}
		}
		;

		//constractors
		Array() {
			width = height = 0;
		};

		Array(unsigned int width, unsigned int height): width(width), height(height) {
		};

		Array(unsigned int width, unsigned int height, const vector<T> * data_ptr) : width(width), height(height) {
			Array<T>::setData(data_ptr);
		};

		Array(const Array &src) {
			Replicate(src);
		};
		//destructor
		~Array() {
			if (!buffer.empty()) {
				buffer.clear();
			}
		};
		
		//operators
		Array<T> & operator = (const Array<T> & right) {
			if (&right == this) {
				return *this;
			}
			if (!buffer.empty()) {
				buffer.clear();
			}
			Replicate(right);
			return *this;
		};

		T& operator ()(unsigned i, unsigned j) {
			return buffer[i*getWidth() + j];
		};
	};
}//namespace array
#endif
