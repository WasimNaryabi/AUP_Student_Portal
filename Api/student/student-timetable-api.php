<?php
include_once '../config/db_connection.php';
	
	class User {
		
		private $db;
		private $connection;
		
		function __construct() {
			$this -> db = new DB_Connection();
			$this -> connection = $this->db->getConnection();
		}
		
		public function AssignmentDetails($discipline_id,$session_id,$semester_id,$section_id,$day_id)
		{
	 $response = array();
						$i = 1;
						$TimeTable['classes']=array();
                    /* query for all records related to this teacher mean faculty_id*/
                   $select_from_table = mysqli_query($this->connection,"SELECT * FROM timetable WHERE section_id = '$section_id' and discipline_id = '$discipline_id' and session_id = '$session_id' and semester_id = '$semester_id' and day_id='$day_id' ORDER BY time_id");
                         while($store_data_timeTable = mysqli_fetch_array($select_from_table)){
                             
                            $tt_id = $store_data_timeTable['timeTable_id'];
                            $faculty_id = $store_data_timeTable['faculty_id'];
                            $session_id = $store_data_timeTable['session_id'];
                            $discipline_id = $store_data_timeTable['discipline_id'];
                            $semester_id = $store_data_timeTable['semester_id'];
                            $section_id = $store_data_timeTable['section_id'];
                                $day_id = $store_data_timeTable['day_id'];
                                $time_id = $store_data_timeTable['time_id'];
                                $sub_id = $store_data_timeTable['subject_id'];
                             
                            $room_id = $store_data_timeTable['room_id'];
                          
                        

                        /*end of all recird fetch from the timetable realated to this faculyt*/

                        /*this is the query for fetching the days*/
                           $select_days =   mysqli_query($this->connection,"SELECT * FROM days WHERE day_id = '$day_id' ");
                           $fetch_days = mysqli_fetch_array($select_days);

                           /*this is the query for fetching the Time*/
                           $select_time =   mysqli_query($this->connection,"SELECT * FROM time WHERE time_id = '$time_id'");
                           $fetch_time = mysqli_fetch_array($select_time);
                                  $time = $fetch_time['strat_time']." -  ".$fetch_time['end_time'];
                           $faculty = mysqli_query($this->connection,"SELECT * FROM faculty where faculty_id = '$faculty_id'");
                           $faculty_fetch = mysqli_fetch_array($faculty); 
                           /*this is the query for fetching the class discipline*/
                           
                           $select_disc =   mysqli_query($this->connection,"SELECT * FROM discipline WHERE discipline_id = '$discipline_id' ");
                           $fetch_disc = mysqli_fetch_array($select_disc);
 

                            /*this is the query for fetching the Subject*/
                           $select_subject =   mysqli_query($this->connection,"SELECT * FROM courses WHERE course_id = '$sub_id' ");
                           $fetch_sub = mysqli_fetch_array($select_subject);

                           /*this is the query for fetching the Subject*/
                           $select_room =   mysqli_query($this->connection,"SELECT * FROM classrooms WHERE room_id = '$room_id'");
                           $fetch_rooms = mysqli_fetch_array($select_room);
                          
						   $gender=$faculty_fetch['gender'];
						   
						   if($gender=="Male" || $gender=="male"){
                            $g="Mr. ";
						   }else{
							$g="Ms. ";
						   }


				$response=array(
						'title_course'=>$fetch_sub['course_name'],
						'room'=>$fetch_rooms['RoomName'],
						'teacher' =>$g.$faculty_fetch['first_name'].' '.$faculty_fetch['last_name'],
						 
						'time'=>$time,
				);
				array_push($TimeTable['classes'],$response);
			
			}
			 
				 
				echo json_encode($TimeTable);
				mysqli_close($this -> connection);
			 
			
		}
		
	}
	
	
	$user = new User();
	if(isset($_POST['discipline_id'],$_POST['session_id'],$_POST['semester_id'],$_POST['section_id'],$_POST['day_id'])) {
		 
		$discipline_id = $_POST['discipline_id'];
		$session_id = $_POST['session_id'];
		$semester_id = $_POST['semester_id'];
		$section_id = $_POST['section_id'];
		$day = $_POST['day_id'];
		 

		//echo $discipline_id."  /  ".$session_id."  /  ".$semester_id."  /  ".$section_id."  /  ".$subject_id;exit();
		if(!empty($discipline_id) && !empty($session_id) && !empty($semester_id) && !empty($section_id) && !empty($day)){
			
		 
			$user-> AssignmentDetails($discipline_id,$session_id,$semester_id,$section_id,$day);
			
		}else{
			echo json_encode("No Record Found");
		}
		
	}
