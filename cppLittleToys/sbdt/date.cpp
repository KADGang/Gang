// date2.cpp : 此文件包含 "main" 函数。程序执行将在此处开始并结束。
//

#include <iostream>
#include "date.h"

date::date(int YEAR, int MONTH, int DAY)
{
	year = YEAR;
	month = MONTH;
	day = DAY;
}

date::date()
{
	year = 0;
	month = 0;
	day = 0;
}

bool date::leap_year(int year)
{
	return (!(year % 4) && (year % 100)) || !((year % 100) || (year % 400));
}

ostream& operator<<(ostream& output, const date& date)
{
	output << date.year << "," << date.month << "," << date.day << endl;
	return output;
}

istream& operator>>(istream& input, date& date)
{
	input >> date.year >> date.month >> date.day;
	return input;
}

bool date::valid()
{
	int leap = leap_year(year);
	if (month > 12 || month <= 0 || day <= 0)
	{
		return 0;
	}
	if (month == 2)
	{
		if (day > DATE[month] + leap)
		{
			return 0;
		}
		else
			return 1;
	}
	else
	{
		if (day > DATE[month])
			return 0;
		else
			return 1;
	}
	return 1;
}

date& date::operator = (const date& date)
{
	if (valid())
	{
		year = date.year;
		month = date.month;
		day = date.day;
	}
	else
		cout << "输入日期不合法" << endl;
	return *this;
}

date date::operator+(int Day)
{
	day += Day;
	while (day > DATE[month] + leap_year(year) * (month == 2))
	{
		day -= DATE[month] + leap_year(year) * (month == 2);
		month++;
		if (month > 12)
		{
			month -= 12;
			year++;
		}
	}
	return *this;
}

date date::operator-(int Day)
{
	day -= Day;
	while (day <= 0)
	{
		month--;
		day += DATE[month] + leap_year(year) * (month == 2);
		if (month == 0)
		{
			year--;
			month = 12;
		}
	}
	return *this;
}

int date::operator-(date& date)
{
	int Day = 0;
	while (!(date == *this))
	{
		date = date + 1;
		Day++;
	}
	return Day;
}

bool date::operator>(date& date)
{
	if (year = date.year)
	{
		if (month = date.month)
			return month > date.month;
		else
			return month > date.month;
	}
	return year > date.month;
}

bool date::operator<(date& date)
{
	if (year = date.year)
	{
		if (month = date.month)
			return month < date.month;
		else
			return month < date.month;
	}
	return year < date.month;
}

bool date::operator==(date& date)
{
	return (year == date.year) && (month == date.month) && (day == date.day);
}