<?php
include_once '../config/db_connection.php';
	
	class User {
		
		private $db;
		private $connection;
		
		function __construct() {
			$this -> db = new DB_Connection();
			$this -> connection = $this->db->getConnection();
		}
		
		public function does_user_exist($email,$password)
		{

			$response = array();
//fetching student table data******************************************************			
			$query = "SELECT * FROM student where student_email ='$email'and password ='$password' ";

			$result1 = mysqli_query($this->connection, $query);
			$result = mysqli_fetch_array($result1);
			$student_id = $result['student_id'];
		     $session_id = $result['session_id'];
		     $discipline_id = $result['discipline_id'];
		     $section_id = $result['section_id'];

//**********Here Fetching session Name ************************************************
		     $session_query = "SELECT * FROM session WHERE session_id = '$session_id;'";
		     $sesssion_result1 = mysqli_query($this->connection, $session_query);
			$session_result = mysqli_fetch_array($sesssion_result1);

			$session_name = $session_result['session_duration'];
//**********Here Fetching discipline Name ************************************************
		     $discipline_query = "SELECT * FROM discipline WHERE discipline_id = '$discipline_id;'";
		     $discipline_result1 = mysqli_query($this->connection, $discipline_query);
			$discipline_result = mysqli_fetch_array($discipline_result1);

			$discipline_name = $discipline_result['discipline_code'];
//**********Here Fetching Section Name ************************************************
		     $section_query = "SELECT * FROM section WHERE section_id = '$section_id;'";
		     $section_result1 = mysqli_query($this->connection, $section_query);
			$section_result = mysqli_fetch_array($section_result1);

			$section_name = $section_result['section_name'];

//**********now fetching history id************************************************
			$select_history_id = "SELECT * FROM student_history WHERE student_id = '$student_id;'";

			$h_result1 = mysqli_query($this->connection, $select_history_id);
			$h_result = mysqli_fetch_array($h_result1);
			$history_id = $h_result['student_history_id'];
			$sem_id = $h_result['semester_id'];
//**********Here Fetching Semester Name ************************************************
		     $semester_query = "SELECT * FROM semester WHERE semester_id = '$sem_id;'";
		     $semester_result1 = mysqli_query($this->connection, $semester_query);
			$semester_result = mysqli_fetch_array($semester_result1);

			$semester_name = $semester_result['semester_name'];

//**********now fetching studend through json***********************************************
            if($result > 0){
				$response=array(
					    'error'=>false,
				        'message'=>'login successfull',
				        'student'=>array(
						'student_id'=>$result['student_id'],
						'uni_reg_number' =>$result['uni_reg_number'],
						'first_name' =>$result['first_name'],
						'last_name' =>$result['last_name'],
						'father_name'=>$result['father_name'],
						'gender' =>$result['gender'],
						'dob'=>$result['date_of_birth'],
                        'classno'=>$result['roll_number'],
						'cnic'=>$result['cnic'],
						'mobno'=>$result['mobile_number'],
						'add'=>$result['address'],
                        'user_type'=> 'Student',
						'history_id'=>$history_id,
						'semester_id'=>$sem_id,
						'session_id'=>$session_id,
						'discipline_id'=>$discipline_id,
						'section_id'=>$discipline_id,
						 
						'semester_name'=>$semester_name,
						'session_name'=>$session_name,
						'discipline_name'=>$discipline_name,
						'section_name'=>$section_name
						)
				);
			}
			else{
				$response=array(
					'error'=>true,
					'message'=>'Invalid UserName or Password',
					'student'=>array(
					'student_id'=>$result['student_id'],
					'uni_reg_number' =>$result['uni_reg_number'],
					'first_name' =>$result['first_name'],
					'last_name' =>$result['last_name'],
					'father_name'=>$result['father_name'],
					'gender' =>$result['gender'],
					'dob'=>$result['date_of_birth'],
					'classno'=>$result['roll_number'],
					'cnic'=>$result['cnic'],
					'mobno'=>$result['mobile_number'],
					'add'=>$result['address'],
					'user_type'=> 'Student',
					'history_id'=>$history_id,
					'semester_id'=>$sem_id,
					'session_id'=>$session_id,
					'discipline_id'=>$discipline_id,
					'section_id'=>$discipline_id,
					 
					'semester_name'=>$semester_name,
					'session_name'=>$session_name,
					'discipline_name'=>$discipline_name,
					'section_name'=>$section_name
					)
			);
			}

				$json['success'] = ' Welcome '.$email;
				echo json_encode($response);
				mysqli_close($this -> connection);
			 
			
		}
		
	}
	
	
	$user = new User();
	if(isset($_POST['uni_reg_number'],$_POST['password'])) {
		$email = $_POST['uni_reg_number'];
		$password = $_POST['password'];
		
		if(!empty($email) && !empty($password)){
			
			$encrypted_password = md5($password);
			$user-> does_user_exist($email,$encrypted_password);
			
		}else{
			echo json_encode("you must type both inputs");
		}
		
	}
