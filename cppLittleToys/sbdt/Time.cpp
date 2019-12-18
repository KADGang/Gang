#include <iostream>
#include "Time.h"

Time::Time()
{
	hour = 0;
	minute = 0;
	second = 0;
};

Time::Time(int Hour, int Minute, int Second)
{
	hour = Hour;
	minute = Minute;
	second = Second;
};

ostream& operator<<(ostream& output, const Time& time)
{
	output << time.hour << ":" << time.minute << ":" << time.second;
	return output;
};

istream& operator>>(istream& input, Time& time)
{
	char x;
	input >> time.hour >> x >> time.minute >> x >> time.second;
	return input;
};

Time Time::operator = (Time& time)
{
	hour = time.hour;
	minute = time.minute;
	second = time.second;
	return *this;
};

Time Time::operator + (Time& time)
{
	hour += time.hour;
	minute += time.minute;
	second += time.second;
	if (second > 60)
	{
		second -= 60;
		minute++;
	}
	if (minute > 60)
	{
		minute -= 60;
		hour++;
	}
	if (hour > 24)
		hour -= 24;
	return *this;
};

Time Time::operator - (int seconds)
{
	second -= seconds;
	if (second < 0)
	{
		second += 60;
		minute--;
	}
	if (minute < 0)
	{
		minute += 60;
		hour--;
	}
	if (hour < 0)
		hour += 24;
	return *this;
};

Time Time::operator + (int seconds)
{
	second += seconds;
	if (second > 60)
	{
		second -= 60;
		minute++;
	}
	if (minute > 60)
	{
		minute -= 60;
		hour++;
	}
	if (hour > 24)
		hour -= 24;
	return *this;
};

Time Time::operator - (Time& time)
{
	hour -= time.hour;
	minute -= time.minute;
	second -= time.second;
	if (second < 0)
	{
		second += 60;
		minute--;
	}
	if (minute < 0)
	{
		minute += 60;
		hour--;
	}
	if (hour < 0)
		hour += 24;
	return *this;
};

bool Time::operator > (Time& time)
{
	if (hour = time.hour)
	{
		if (minute = time.minute)
			return second > time.second;
		else
			return minute > time.minute;
	}
	return hour > time.hour;
};

bool Time::operator < (Time& time)
{
	if (hour = time.hour)
	{
		if (minute = time.minute)
			return second < time.second;
		else
			return minute < time.minute;
	}
	return hour < time.hour;
};

bool Time::operator == (Time& time)
{
	return (hour == time.hour) && (minute == time.minute) && (second == time.second);
};
