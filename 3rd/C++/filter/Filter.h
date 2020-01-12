#ifndef _FILTER
#define _FILTER
#include <iostream>
#include "Array.h"
#include "Image.h"
using namespace math;
using namespace std;
class Filter {
	public:
		//constuctors
		Filter() {}
		Filter(const Filter &f) { }
		//operator
		virtual Image operator << (const Image & image) = 0;
};
#endif