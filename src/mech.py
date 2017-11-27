import numpy as np


def retaining_ring_factor_of_safety(yield_strength, groove_diameter, ring_diameter, force):
	area = np.pi * (ring_diameter **2  - groove_diameter**2)
	pressure = force / area
	return yield_strength/pressure

def groove_factor_of_safety_upper(force, shaft_diameter, groove_diameter, groove_width, corner_radius):
	a = groove_width
	r = corner_radius
	t = shaft_diameter - groove_diameter
	print("a/t = ", a/t)
	print("r/t = ", r/t)
	Kt = 5.4
	q = 0.85
	Kf = 1 + q * (Kt - 1)
	sigma_o = 4 * force / (np.pi * groove_diameter)
	sigma_max = Kf * sigma_o
	print(sigma_max)

def groove_factor_of_safety_lower(force, shaft_diameter, groove_diameter, groove_width, corner_radius):
	a = groove_width
	r = corner_radius
	t = shaft_diameter - groove_diameter
	print("a/t = ", a/t)
	print("r/t = ", r/t)
	Kt = 6.3
	q = 0.85
	Kf = 1 + q * (Kt - 1)
	sigma_o = 4 * force / (np.pi * groove_diameter)
	sigma_max = Kf * sigma_o
	print(sigma_max, "psi")

def main():
	force = 89.9236 #lbf

	#15-7 PH Stainless Steel 1 inch $8.54 for 5 retaining rings
	#Can be used to secure pulley and power screw
	#https://www.mcmaster.com/#retaining-rings/=1afmdd1
	upper_ring_cost = 8.54 #dollars (get 5 of them)
	upper_groove_cost = (10 + 0.5 * (25.4 - 10)) * 4
	total_upper_cost = upper_ring_cost + upper_groove_cost
	upper_shaft_diameter = 1 #inch
	groove_diameter_upper = 0.94 #inch
	groove_width_upper = 0.046 #inch
	ring_thickness_upper = 0.042 * 2 #inch
	ring_outer_diameter_upper = groove_diameter_upper + ring_thickness_upper
	yield_strength_upper = 200000 * 0.577 #http://www.kvastainless.com/15-7.html	
	retaining_ring_upper_f_o_s = retaining_ring_factor_of_safety(yield_strength_upper, groove_diameter_upper, ring_outer_diameter_upper, force)
	print("Factor of safety upper", retaining_ring_upper_f_o_s)
	print("Cost of 4 upper bearings", total_upper_cost)
	groove_factor_of_safety_upper(force, upper_shaft_diameter, groove_diameter_upper, groove_width_upper, 0.01)
	print("\n")
	#https://www.mcmaster.com/#retaining-rings/=1afj43v
	lower_ring_cost = 5.20 * 2 #dollars
	lower_groove_cost = (10 + 0.5 * (50.8 - 10)) * 2
	total_lower_cost = lower_groove_cost + lower_ring_cost
	lower_shaft_diameter = 2 #inch
	groove_diameter_lower = 1.886 #inch
	groove_width_lower = 0.068 #inch
	ring_thickness_lower = 0.062 * 2 #inch
	ring_outer_diameter_lower = groove_diameter_lower + ring_thickness_lower
	yield_strength_lower = 200000 * 0.577
	retaining_ring_lower_f_o_s = retaining_ring_factor_of_safety(yield_strength_lower, groove_diameter_lower, ring_outer_diameter_lower, force)
	print("Factor of safety lower", retaining_ring_lower_f_o_s)
	print("Cost of 2 lower Bearings", total_lower_cost)
	groove_factor_of_safety_lower(force, lower_shaft_diameter, groove_diameter_lower, groove_width_lower, 0.01)

if __name__ == "__main__":
	main()