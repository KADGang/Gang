#pragma once
#include <iostream>

using namespace std;

class Time {
private:
	int hour;
	int minute;
	int second;
public:
	Time();
	Time(int Hour, int Minute, int Second);
	friend ostream& operator<<(ostream& output, const Time& time);
	friend istream& operator>>(istream& input, Time& time);
	Time operator = (Time& time);
	Time operator + (Time& time);
	Time operator - (int time);
	Time operator + (int time);
	Time operator - (Time& time);
	bool operator > (Time& time);
	bool operator < (Time& time);
	bool operator == (Time& time);
};